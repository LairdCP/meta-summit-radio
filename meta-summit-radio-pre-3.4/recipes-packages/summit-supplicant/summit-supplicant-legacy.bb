SUMMARY = "Summit Supplicant legacy"

DEFCONFIG = "config_legacy"

require summit-supplicant.inc
require radio-stack-msd-version.inc

DEPENDS += "summit-supplicant-libs-legacy"
