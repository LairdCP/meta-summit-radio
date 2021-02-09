SUMMARY = "Laird Connectivity Sterling 60 Firmware PCIE-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "3419307f4f2a1b6978cf0018174996e6"
SRC_URI[sha256sum] = "0a2d1ee3b8c74367a2ba2b12854bc3835aa7691d2e1efb3654e73ca65d2de09e"

include radio-firmware.inc
