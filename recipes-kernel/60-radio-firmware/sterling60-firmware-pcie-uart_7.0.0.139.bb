SUMMARY = "Laird Sterling 60 Firmware PCIE-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "50b68b8fbe2a77cdff99608a14dae2b9"
SRC_URI[sha256sum] = "01a2ca019027ddab4ce0906966061963618708649f9be4e37b9bb093581f2b3b"

include radio-firmware.inc
