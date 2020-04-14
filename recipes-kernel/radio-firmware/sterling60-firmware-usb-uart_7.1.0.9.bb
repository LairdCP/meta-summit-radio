SUMMARY = "Laird Connectivity Sterling 60 Firmware USB-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "2ba32257f9d3c359d8f1cde871f3aaf8"
SRC_URI[sha256sum] = "7ffc142591ab3a79ec96eb44471c067158e269ad164109a8b9ea6a545c7500a4"

include radio-firmware.inc
