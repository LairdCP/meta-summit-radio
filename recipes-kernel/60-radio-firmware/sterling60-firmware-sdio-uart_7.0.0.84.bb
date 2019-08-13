SUMMARY = "Laird Sterling 60 Firmware SDIO-UART"

SRC_URI += "${LRD_URI_BASE}/laird-sterling60-firmware-sdio-uart-${PV}.tar.bz2"

SRC_URI[md5sum] = "0e3ce96a9c03033c99c250fca3f57306"
SRC_URI[sha256sum] = "5b5075af2a51e22dd413a23c7f170f0349b7074ca73d2c83057986f910a15e6b"

include radio-firmware.inc
