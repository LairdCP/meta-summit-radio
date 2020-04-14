SUMMARY = "Laird Connectivity Sterling 60 Firmware SDIO-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "731399606f2c6ee3d52c6b1f40bde3ed"
SRC_URI[sha256sum] = "4227d089b71e674e4f27aa4ccaa917b40ab9c844ac1db041681c65a7244a2bbf"

include radio-firmware.inc
