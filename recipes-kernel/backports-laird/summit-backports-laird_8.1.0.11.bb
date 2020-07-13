SUMMARY = "Laird Connectivity Wi-Fi Backports for Summit 60"

BACKPORTS_CONFIG = "defconfig-summit60"

SRC_URI += "${LRD_60_URI_BASE}/${BACKPORTS_FILE}"

SRC_URI[md5sum] = "b5c26efbfde67351a6fa53a06ed77c8c"
SRC_URI[sha256sum] = "d895e0a1c19b7248467022bd98a62f9b95f324e47fde7d1585d6e6c75065f852"

RREPLACES_${PN} = "sterling-backports-laird"
RCONFLICTS_${PN} = "sterling-backports-laird lwb-backports-laird"

include backports-laird.inc
