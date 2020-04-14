SUMMARY = "Laird Connectivity Wi-Fi Backports for LWB"

BACKPORTS_CONFIG = "defconfig-lwb"

SRC_URI += "${LRD_LWB_URI_BASE}/${BACKPORTS_FILE}"
SRC_URI += "file://0001-backports-suppress-attribute-cold-warnings.patch;striplevel=2"

SRC_URI[md5sum] = "3fb119ea9efa0e190023e4db5f022467"
SRC_URI[sha256sum] = "5f2c9c7c82859a21bfb8ae3af7b71da49b132d144b97b70858aa0daddd4d8642"

RREPLACES_${PN} = "summit-backports-laird"
RCONFLICTS_${PN} = "summit-backports-laird sterling-backports-laird"

include backports-laird.inc
