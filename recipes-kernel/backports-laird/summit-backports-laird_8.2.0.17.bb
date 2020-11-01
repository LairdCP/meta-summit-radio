SUMMARY = "Laird Connectivity Wi-Fi Backports for Summit 60"

BACKPORTS_CONFIG = "defconfig-summit60"

SRC_URI += "${LRD_60_URI_BASE}/${BACKPORTS_FILE}"

SRC_URI[md5sum] = "ed8a0a89b3403ef8ba662515e05b8f77"
SRC_URI[sha256sum] = "47f08dac6b0a4bff67d0bb6ebbd6cbc56d636a50947300592b527db3e659bf38"

RCONFLICTS_${PN} = "sterling-backports-laird lwb-backports-laird lwb5p-backports-laird"

include backports-laird.inc
