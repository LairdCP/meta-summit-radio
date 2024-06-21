SUMMARY = "Summit Wi-Fi LWB & LWB5 Manufacturing tools"

REG_NAME = "regCypress"

require mfg-reg.inc radio-stack-lwb-version.inc

LICENSE = "Cypress"
NO_GENERIC_LICENSE[Cypress] = "FOSS_README.txt"
LIC_FILES_CHKSUM = "file://FOSS_README.txt;md5=02c65bdd6b6f7dac25fea2a4a2d18c51"

do_install () {
	install -D -m 775 ${B}/wl ${D}${bindir}/wl
	install -D -m 644 -t ${D}${nonarch_base_libdir}/firmware/brcfmac ${B}/brcmfmac*
}
