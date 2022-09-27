SUMMARY = "Summit Wi-Fi LWB & LWB5 Manufacturing tools"

REG_NAME = "regCypress"

require mfg-reg.inc radio-stack-lwb-version.inc

do_install () {
	install -D -m 775 ${B}/wl ${D}${bindir}/wl

	install -D -m 644 -t ${D}${nonarch_base_libdir}/firmware/brcfmac ${B}/brcmfmac*
}
