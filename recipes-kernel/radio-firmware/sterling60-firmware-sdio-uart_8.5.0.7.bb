SUMMARY = "Laird Connectivity Sterling 60 Firmware SDIO-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "c9fc5b25bbf09901f1307a28b4c94e93"
SRC_URI[sha256sum] = "d85c9be6b559effcf216bce7baada039894b70efa91f0b3b56d1357d689edb95"

include radio-firmware-60.inc
