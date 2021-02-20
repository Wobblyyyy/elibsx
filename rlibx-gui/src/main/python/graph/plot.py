#
#  Copyright (c) 2020, Colin Robertson (wobblyyyy@gmail.com)
#
#  This file is part of the elibsx project. The elibsx project is licensed
#  under the GNU General Public License V3.
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the license is distributed on an "AS IS" BASIS, WITHOUT
#  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
#  license for the specific language governing permissions and limitations
#  under the License.
#
#  Along with this file, you should have received a license file, containing
#  a copy of the GNU General Public License V3. If you did not receive a copy
#  of that license, you may find one online.
#
#  elibsx GitHub repository:
#  https://github.com/Wobblyyyy/elibsx
#
#  GNU General Public License V3:
#  http://www.gnu.org/licenses/gpl-3.0.en.html
#
#

# import matplotlib.pyplot as plt
# import numpy as np
# import gui
#
# fig, ax = plt.subplots()  # Create a figure containing a single axes.
# ax.plot([1, 2, 3, 4], [1, 4, 2, 3])  # Plot some data on the axes.
#
# root = gui.root

import numpy as np
import tkinter
# Implement the default Matplotlib key bindings.
from matplotlib.backend_bases import key_press_handler
from matplotlib.backends.backend_tkagg import (
    FigureCanvasTkAgg, NavigationToolbar2Tk)
from matplotlib.figure import Figure

root = tkinter.Tk()
root.wm_title("Embedding in Tk")

fig = Figure(figsize=(5, 4), dpi=100)
t = np.arange(0, 3, .01)
fig.add_subplot(111).plot(t, 2 * np.sin(2 * np.pi * t))

canvas = FigureCanvasTkAgg(fig, master=root)  # A tk.DrawingArea.
canvas.draw()
canvas.get_tk_widget().pack(side=tkinter.TOP, fill=tkinter.BOTH, expand=1)

toolbar = NavigationToolbar2Tk(canvas, root)
toolbar.update()
canvas.get_tk_widget().pack(side=tkinter.TOP, fill=tkinter.BOTH, expand=1)


def on_key_press(event):
    print("you pressed {}".format(event.key))
    key_press_handler(event, canvas, toolbar)


canvas.mpl_connect("key_press_event", on_key_press)


def _quit():
    root.quit()  # stops mainloop
    root.destroy()  # this is necessary on Windows to prevent
    # Fatal Python Error: PyEval_RestoreThread: NULL tstate


button = tkinter.Button(master=root, text="Quit", command=_quit)
button.pack(side=tkinter.BOTTOM)

tkinter.mainloop()
# If you put root.destroy() here, it will cause an error if the window is
# closed with the window manager.
