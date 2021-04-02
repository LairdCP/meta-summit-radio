SUMMARY = "Laird Connectivity firmware for the LWB5+ SDIO/UART module with diversity antenna"

SRC_URI += "${LRD_LWB_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "38c264e40a5fab539118d78e9a9a97a9"
SRC_URI[sha256sum] = "b7bd38945834e0536eb922188e076f276ccb2b1076ba2ab642bf70eccbfa1d4a"

include radio-firmware-lwb.inc
