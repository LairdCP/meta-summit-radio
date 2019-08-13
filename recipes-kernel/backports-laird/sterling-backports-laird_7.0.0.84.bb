SUMMARY = "Laird Wi-Fi Backports for Sterling 60"

BACKPORTS_CONFIG = "defconfig-sterling60"

SRC_URI += "${LRD_URI_BASE}/${BACKPORTS_FILE}"

SRC_URI[md5sum] = "e1bdb2f83fa86acd65c5f8ee019db8d4"
SRC_URI[sha256sum] = "194a3546beff23f17b0cba41f9ac472d594be2cc2e80f6f2d19cb6acb86434cf"

RREPLACES_${PN} = "summit-backports-laird"
RCONFLICTS_${PN} = "summit-backports-laird lwb-backports-laird"

include backports-laird.inc
