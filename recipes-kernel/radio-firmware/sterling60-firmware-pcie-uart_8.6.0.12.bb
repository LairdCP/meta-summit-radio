SUMMARY = "Laird Connectivity Sterling 60 Firmware PCIE-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "2631c6eec612567fc6c028b5af392661"
SRC_URI[sha256sum] = "259bc639b2e9790987963b3b1d89a2d9bd3c9c20f3b22666889b0f487ae0d9d9"

include radio-firmware-60.inc
