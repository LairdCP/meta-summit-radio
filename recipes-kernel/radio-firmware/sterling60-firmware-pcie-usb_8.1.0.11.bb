SUMMARY = "Laird Connectivity Sterling 60 Firmware PCIE-USB"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "06bc1fcf53d53e239c6c41e442d53aa4"
SRC_URI[sha256sum] = "6af643d24d5f593a83b61a609ca9378edfb9b04a31d4e53e09eb59a9709633f7"

include radio-firmware.inc
