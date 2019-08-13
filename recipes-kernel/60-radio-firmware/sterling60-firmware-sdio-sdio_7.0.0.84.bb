SUMMARY = "Laird Sterling 60 Firmware SDIO-SDIO"

SRC_URI += "${LRD_URI_BASE}/laird-sterling60-firmware-sdio-sdio-${PV}.tar.bz2"

SRC_URI[md5sum] = "dc3f66184f19f68c228d20926a43c8f3"
SRC_URI[sha256sum] = "a10f1ec236e283a770aca3ea33d2e11f71e0e723feb13b131b04a1caf9680d2c"

include radio-firmware.inc
