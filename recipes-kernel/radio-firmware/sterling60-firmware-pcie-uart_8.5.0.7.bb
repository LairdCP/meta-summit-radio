SUMMARY = "Laird Connectivity Sterling 60 Firmware PCIE-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "88be70d2d60a6667fe11ea3b17929516"
SRC_URI[sha256sum] = "c7a1c9c6eaa38240e2a9b2293e759ec3d3f7d543e2bd9b59698c526615733db6"

include radio-firmware-60.inc
