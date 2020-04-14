SUMMARY = "Laird Connectivity Sterling 60 Firmware PCIE-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "c7afc09e339119fa8f9bb471873e844a"
SRC_URI[sha256sum] = "49284b7171c7784b5281cd3646f2cda524d50c801869ad7299fb50c74ef8fb40"

include radio-firmware.inc
