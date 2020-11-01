SUMMARY = "Laird Connectivity LWB5+ firmware for SDIO/UART single antenna module for operation in Australia"

SRC_URI += "${LRD_URI_LOCAL}/laird-${BPN}-${PV}.tar.bz2"

include radio-firmware.inc
