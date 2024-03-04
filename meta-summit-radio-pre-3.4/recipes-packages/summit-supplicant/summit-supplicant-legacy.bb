SUMMARY = "Summit Supplicant legacy"

DEFCONFIG = "config_legacy"

require summit-supplicant.inc
require radio-stack-4550-version.inc

DEPENDS += "summit-supplicant-libs-legacy"
