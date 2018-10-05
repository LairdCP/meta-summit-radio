
def make_dir(v):
    vers = v.split(".")
    l = len(vers)
    if l > 1:
        vers[l - 1] = "x"
        return ".".join(vers)
    else:
        return v

LRD_URI_BASE = "https://github.com/LairdCP/Release-Packages/raw/master/ST60/${@make_dir("${PV}")}/${PV}"
