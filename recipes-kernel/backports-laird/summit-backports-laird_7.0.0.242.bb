SUMMARY = "Laird Wi-Fi Backports for Summit 60"

BACKPORTS_CONFIG = "defconfig-summit60"

SRC_URI += "${LRD_60_URI_BASE}/${BACKPORTS_FILE}"

SRC_URI[md5sum] = "3d791e1d32d601ce17dbdab70057f249"
SRC_URI[sha256sum] = "68e1e290e34cf611a0726d77445c8dc2b31a7071a6272dd900f0d4379cb949bd"

RREPLACES_${PN} = "sterling-backports-laird"
RCONFLICTS_${PN} = "sterling-backports-laird lwb-backports-laird"

include backports-laird.inc

