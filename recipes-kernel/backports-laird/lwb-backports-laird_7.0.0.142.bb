SUMMARY = "Laird Wi-Fi Backports for LWB"

BACKPORTS_CONFIG = "defconfig-lwb"

SRC_URI += "${LRD_LWB_URI_BASE}/${BACKPORTS_FILE}"

SRC_URI[md5sum] = "94ed56788ba0fb7313d19b8e1d53dbf0"
SRC_URI[sha256sum] = "7331c6d0e7f36aaf4f7553ecb10b0bb59d2a25789137f2383294364ef2c9ab5f"

RREPLACES_${PN} = "summit-backports-laird"
RCONFLICTS_${PN} = "summit-backports-laird sterling-backports-laird"

include backports-laird.inc
