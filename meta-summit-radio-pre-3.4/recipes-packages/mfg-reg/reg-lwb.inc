require mfg-reg.inc radio-stack-lwb-version.inc

do_install() {
	install -D -m 775 ${B}/lru ${D}${bindir}/lru

	if ${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', 'true', 'false', d)} && [ -f ${B}/btlru ]; then
		install -D -m 775 ${B}/btlru ${D}${bindir}/btlru
	fi

	install -D -m 644 -t ${D}${nonarch_base_libdir}/firmware/brcfmac ${B}/brcmfmac*
	install -D -m 644 -t ${D}${nonarch_base_libdir}/firmware/cypress ${B}/cyfmac*
}
