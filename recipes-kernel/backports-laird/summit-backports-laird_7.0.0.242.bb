SUMMARY = "Laird Wi-Fi Backports for Summit 60"

BACKPORTS_CONFIG = "defconfig-summit60"

SRC_URI += "${LRD_60_URI_BASE}/${BACKPORTS_FILE}"

SRC_URI[md5sum] = "e78530f59c2f35a575d441ab3beeaa5e"
SRC_URI[sha256sum] = "b5939f0e5cfc61717b03eb943d3c99c89fd0784c65f9ebacdff069f0da4a32ca"

RREPLACES_${PN} = "sterling-backports-laird"
RCONFLICTS_${PN} = "sterling-backports-laird lwb-backports-laird"

include backports-laird.inc

