SUMMARY = "Laird Sterling 60 Firmware PCIE-UART"

SRC_URI += "${LRD_URI_BASE}/laird-sterling60-firmware-pcie-uart-${PV}.tar.bz2"

SRC_URI[md5sum] = "ea1b5ba8be0fdcbdd460cec880d863e3"
SRC_URI[sha256sum] = "1aedeb53069cdcf7572c7234372d3de9b8198c02f05a141275387038447a1b00"

include radio-firmware.inc
