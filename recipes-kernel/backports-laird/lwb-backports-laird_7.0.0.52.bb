SUMMARY = "Laird Wi-Fi Backports for LWB"

BACKPORTS_CONFIG = "defconfig-lwb-fcc"

RCONFLICTS_${PN} = "sterling-backports-laird summit-backports-laird"

SRC_URI += "${LRD_URI_LOCAL}/backports-laird-930-0075.zip"

include backports-laird.inc

do_unpack_append() {
    import tarfile
    fname = d.expand(d.getVar("BACKPORTS_FILE", True))
    tf = tarfile.open(fname)
    tf.extractall()
}
