SUMMARY = "Laird Connectivity Wi-Fi Backports for Summit 60"

BACKPORTS_CONFIG = "defconfig-summit60"

SRC_URI += "${LRD_60_URI_BASE}/${BACKPORTS_FILE}"

SRC_URI[md5sum] = "fd437a01a6cfba0a87ab2ed3eb15800b"
SRC_URI[sha256sum] = "df98bda9b307cb5f7150f5583c27d01d5569dcccb67e7da2e86830939a71d6ff"

RCONFLICTS_${PN} = "sterling-backports-laird lwb-backports-laird lwb5p-backports-laird"

include backports-laird.inc
