from Tkinter import *
import os
import json

# =========================================================GUI========================================================= #
win = Tk()
win.title("BShelper")

win.geometry("600x300")

win.config(bg="#323232")

labelName = Label(text="Registry Name", fg="white", bg="#323232")
labelName.config(font=("Microsoft JhengHei", 10))
labelName.pack()
entryName = Entry()
entryName.pack()

labelType = Label(text="BlockState Type", fg="white", bg="#323232")
labelType.config(font=("Microsoft JhengHei", 10))
labelType.pack()
listbox = Listbox(win)
listbox.insert(0, "base")
listbox.insert(1, "four")
listbox.insert(2, "six")
listbox.insert(3, "stair")
listbox.insert(4, "side_slab")
listbox.insert(5, "slab_half")
listbox.pack()


labelState = Label(text="Waiting", fg="white", bg="#323232")
labelState.config(font=("Microsoft JhengHei", 10))
labelState.pack()


def gen(name="", type=""):
    if name == "" or type == "":
        labelState.config(text="Missing Arguments", fg="red", bg="#323232")
        return
    # =========================================================FORMATS========================================================= #
    # Base Block Model, ie: grass, dirt, stone.
    base = {
        "variants": {
            "normal": {
                "model": "eki:"+name
            }
        }
    }

    # Blocks that have four faces: N-E-S-W, ie: furnace, chest (single).
    # Block model should base on north side.
    four = {
        "variants": {
            "facing=north": {
                "model": "eki:"+name,
                "y": 180
            },
            "facing=west": {
                "model": "eki:"+name,
                "y": 90
            },
            "facing=south": {
                "model": "eki:"+name
            },
            "facing=east": {
                "model": "eki:"+name,
                "y": 270
            }
        }
    }

    # Blocks that have six faces: N-E-S-W-U-D, ie: logs.
    # Block model should base on north side.
    six = {
        "variants": {
            "facing=up": {
                "model": "eki:"+name
            },
            "facing=down": {
                "model": "eki:"+name,
                "x": 180
            },
            "facing=east": {
                "model": "eki:"+name,
                "y": 90,
                "x": 90
            },
            "facing=south": {
                "model": "eki:"+name,
                "y": 180,
                "x": 90
            },
            "facing=west": {
                "model": "eki:"+name,
                "y": 270,
                "x": 90
            },
            "facing=north": {
                "model": "eki:"+name,
                "y": 0,
                "x": 90
            }
        }
    }

    # For stairs, should pass original block registry name since this already transform into stair registry name, ie: "eki:" + name + "_[(inner)/(outer)]_stairs"
    stair = {
        "variants": {
            "facing=east,half=bottom,shape=straight": {
                "model": "eki:"+name+"_stairs"
            },
            "facing=west,half=bottom,shape=straight": {
                "model": "eki:"+name+"_stairs",
                "y": 180,
                "uvlock": True
            },
            "facing=south,half=bottom,shape=straight": {
                "model": "eki:"+name+"_stairs",
                "y": 90,
                "uvlock": True
            },
            "facing=north,half=bottom,shape=straight": {
                "model": "eki:"+name+"_stairs",
                "y": 270,
                "uvlock": True
            },
            "facing=east,half=bottom,shape=outer_right": {
                "model": "eki:"+name+"_outer_stairs"
            },
            "facing=west,half=bottom,shape=outer_right": {
                "model": "eki:"+name+"_outer_stairs",
                "y": 180,
                "uvlock": True
            },
            "facing=south,half=bottom,shape=outer_right": {
                "model": "eki:"+name+"_outer_stairs",
                "y": 90,
                "uvlock": True
            },
            "facing=north,half=bottom,shape=outer_right": {
                "model": "eki:"+name+"_outer_stairs",
                "y": 270,
                "uvlock": True
            },
            "facing=east,half=bottom,shape=outer_left": {
                "model": "eki:"+name+"_outer_stairs",
                "y": 270,
                "uvlock": True
            },
            "facing=west,half=bottom,shape=outer_left": {
                "model": "eki:"+name+"_outer_stairs",
                "y": 90,
                "uvlock": True
            },
            "facing=south,half=bottom,shape=outer_left": {
                "model": "eki:"+name+"_outer_stairs"
            },
            "facing=north,half=bottom,shape=outer_left": {
                "model": "eki:"+name+"_outer_stairs",
                "y": 180,
                "uvlock": True
            },
            "facing=east,half=bottom,shape=inner_right": {
                "model": "eki:"+name+"_inner_stairs"
            },
            "facing=west,half=bottom,shape=inner_right": {
                "model": "eki:"+name+"_inner_stairs",
                "y": 180,
                "uvlock": True
            },
            "facing=south,half=bottom,shape=inner_right": {
                "model": "eki:"+name+"_inner_stairs",
                "y": 90,
                "uvlock": True
            },
            "facing=north,half=bottom,shape=inner_right": {
                "model": "eki:"+name+"_inner_stairs",
                "y": 270,
                "uvlock": True
            },
            "facing=east,half=bottom,shape=inner_left": {
                "model": "eki:"+name+"_inner_stairs",
                "y": 270,
                "uvlock": True
            },
            "facing=west,half=bottom,shape=inner_left": {
                "model": "eki:"+name+"_inner_stairs",
                "y": 90,
                "uvlock": True
            },
            "facing=south,half=bottom,shape=inner_left": {
                "model": "eki:"+name+"_inner_stairs"
            },
            "facing=north,half=bottom,shape=inner_left": {
                "model": "eki:"+name+"_inner_stairs",
                "y": 180,
                "uvlock": True
            },
            "facing=east,half=top,shape=straight": {
                "model": "eki:"+name+"_stairs",
                "x": 180,
                "uvlock": True
            },
            "facing=west,half=top,shape=straight": {
                "model": "eki:"+name+"_stairs",
                "x": 180,
                "y": 180,
                "uvlock": True
            },
            "facing=south,half=top,shape=straight": {
                "model": "eki:"+name+"_stairs",
                "x": 180,
                "y": 90,
                "uvlock": True
            },
            "facing=north,half=top,shape=straight": {
                "model": "eki:"+name+"_stairs",
                "x": 180,
                "y": 270,
                "uvlock": True
            },
            "facing=east,half=top,shape=outer_right": {
                "model": "eki:"+name+"_outer_stairs",
                "x": 180,
                "y": 90,
                "uvlock": True
            },
            "facing=west,half=top,shape=outer_right": {
                "model": "eki:"+name+"_outer_stairs",
                "x": 180,
                "y": 270,
                "uvlock": True
            },
            "facing=south,half=top,shape=outer_right": {
                "model": "eki:"+name+"_outer_stairs",
                "x": 180,
                "y": 180,
                "uvlock": True
            },
            "facing=north,half=top,shape=outer_right": {
                "model": "eki:"+name+"_outer_stairs",
                "x": 180,
                "uvlock": True
            },
            "facing=east,half=top,shape=outer_left": {
                "model": "eki:"+name+"_outer_stairs",
                "x": 180,
                "uvlock": True
            },
            "facing=west,half=top,shape=outer_left": {
                "model": "eki:"+name+"_outer_stairs",
                "x": 180,
                "y": 180,
                "uvlock": True
            },
            "facing=south,half=top,shape=outer_left": {
                "model": "eki:"+name+"_outer_stairs",
                "x": 180,
                "y": 90,
                "uvlock": True
            },
            "facing=north,half=top,shape=outer_left": {
                "model": "eki:"+name+"_outer_stairs",
                "x": 180,
                "y": 270,
                "uvlock": True
            },
            "facing=east,half=top,shape=inner_right": {
                "model": "eki:"+name+"_inner_stairs",
                "x": 180,
                "y": 90,
                "uvlock": True
            },
            "facing=west,half=top,shape=inner_right": {
                "model": "eki:"+name+"_inner_stairs",
                "x": 180,
                "y": 270,
                "uvlock": True
            },
            "facing=south,half=top,shape=inner_right": {
                "model": "eki:"+name+"_inner_stairs",
                "x": 180,
                "y": 180,
                "uvlock": True
            },
            "facing=north,half=top,shape=inner_right": {
                "model": "eki:"+name+"_inner_stairs",
                "x": 180,
                "uvlock": True
            },
            "facing=east,half=top,shape=inner_left": {
                "model": "eki:"+name+"_inner_stairs",
                "x": 180,
                "uvlock": True
            },
            "facing=west,half=top,shape=inner_left": {
                "model": "eki:"+name+"_inner_stairs",
                "x": 180,
                "y": 180,
                "uvlock": True
            },
            "facing=south,half=top,shape=inner_left": {
                "model": "eki:"+name+"_inner_stairs",
                "x": 180,
                "y": 90,
                "uvlock": True
            },
            "facing=north,half=top,shape=inner_left": {
                "model": "eki:"+name+"_inner_stairs",
                "x": 180,
                "y": 270, "uvlock": True
            }
        }
    }

    # Block that is side slab, ez to realize , bruh
    side_slab = {
        "variants": {
            "facing=north": {
                "model": "eki:"+name,
                "y": 180
            },
            "facing=west": {
                "model": "eki:"+name,
                "y": 90
            },
            "facing=south": {
                "model": "eki:"+name,
            },
            "facing=east": {
                "model": "eki:"+name,
                "y": 270
            }
        }
    }

    # Block that is slab, bruh. But this format only produces half format, double format is provided by base format.
    slab_half = {
        "variants":
        {
            "half=bottom,variant=default": {
                "model": "eki:"+name+"_slab_bottom"
            },
            "half=top,variant=default": {
                "model": "eki:"+name+"_slab_top"
            }
        }
    }

    item = {
        "parent": "eki:block/tessera_orange_fire"
    }
    # =========================================================END========================================================= #

    # =========================================================FILE FORMAT========================================================= #
    fileFormats = {
        "base": "",
        "four": "",
        "six": "",
        "stair": "_stairs",
        "side_slab": "_side_slab",
        "slab": [
            "_slab_half",
            "_slab_double"
        ]
    }
    # =========================================================END========================================================= #

    path = os.path.abspath(os.path.dirname(__file__))
    if (not os.path.exists("./blockstates")) :
        os.makedirs("blockstates")

    if type == "slab":
        formatToWrite = [slab_half, base]
        i = 0

        while i < 2:
            with open(path+"/blockstates/"+name+fileFormats["slab"][i]+".json", 'w') as f:
                f.write(json.dumps(formatToWrite[i], sort_keys=True,
                                   indent=4, ensure_ascii=False) + "\n")
            i = i+1
    else:
        formatToWrite = {
            "base": base,
            "four": four,
            "six": six,
            "stair": stair,
            "side_slab": side_slab
        }[type]

        fileFormatToWrite = fileFormats[type]

        with open(path+"/blockstates/"+name+fileFormatToWrite+".json", 'w') as f:
            f.write(json.dumps(formatToWrite, sort_keys=True,
                               indent=4, ensure_ascii=False) + "\n")


btn = Button(text="Generate", command=lambda: gen(
    name=entryName.get(), type=listbox.get(ACTIVE)))
btn.pack()

labelWarning = Label(
    text="!!You should place this file in the assets/<modid>!!", fg="red", bg="#323232")
labelWarning.config(font=("Microsoft JhengHei", 10))
labelWarning.pack()

labelState.config(text="Waiting", fg="white", bg="#323232")

# Loop Progress
win.mainloop()
# =========================================================END========================================================= #
