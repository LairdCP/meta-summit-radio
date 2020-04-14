SUMMARY = "Laird Connectivity Wi-Fi Backports for Sterling 60"

BACKPORTS_CONFIG = "defconfig-sterling60"

SRC_URI += "${LRD_60_URI_BASE}/${BACKPORTS_FILE}"
SRC_URI += "file://0001-backports-suppress-attribute-cold-warnings.patch;striplevel=2"

SRC_URI[md5sum] = "fe943430cb4bc788d12d45829bc64314"
SRC_URI[sha256sum] = "a4c0fec2e1c3a05b8111aec31bf4274bb5dd5c6c405613e73e0fc4d3090787ef"

RREPLACES_${PN} = "summit-backports-laird"
RCONFLICTS_${PN} = "summit-backports-laird lwb-backports-laird"

include backports-laird.inc
