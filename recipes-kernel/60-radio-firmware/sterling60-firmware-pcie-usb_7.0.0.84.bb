SUMMARY = "Laird Sterling 60 Firmware PCIE-USB"

SRC_URI += "${LRD_URI_BASE}/laird-sterling60-firmware-pcie-usb-${PV}.tar.bz2"

SRC_URI[md5sum] = "d3002f8ac84284601b177797c978e8dc"
SRC_URI[sha256sum] = "ad731d3779dd84da3d257d0e570e2bc4883f030e9f77a89249be67d28630ad9e"

include radio-firmware.inc
