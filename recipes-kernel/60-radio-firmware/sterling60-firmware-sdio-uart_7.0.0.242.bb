SUMMARY = "Laird Sterling 60 Firmware SDIO-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "678cdf840a8e15854109d9a37ec04d88"
SRC_URI[sha256sum] = "381020dcfe71a20d17e07afd8ced7db25abf6869402baf362c2f3ef3cb5e4980"

include radio-firmware.inc
