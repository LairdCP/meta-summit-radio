SUMMARY = "Laird Connectivity Sterling 60 Firmware PCIE-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "e98581f0233ea0283d09a6739bfc6c36"
SRC_URI[sha256sum] = "5a625433d62167ca36f91c27b0a0f4c060ae09f3f60ac9759a0acc5c2ca97931"

include radio-firmware.inc
