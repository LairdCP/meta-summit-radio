require mfg-reg.inc radio-stack-lwb-version.inc

LIC_FILES_CHKSUM += "file://FOSS_README.txt;md5=02c65bdd6b6f7dac25fea2a4a2d18c51"

do_install() {
	install -D -t "${D}${bindir}" -m 775 "${B}/lru"

	if ${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', 'true', 'false', d)} && [ -f "${B}/btlru" ]; then
		install -D -t "${D}${bindir}" -m 775 "${B}/btlru"
	fi

	if ls "${B}/brcmfmac"* >/dev/null 2>&1 ; then
		install -D -t "${D}${nonarch_base_libdir}/firmware/brcfmac" -m 644 "${B}/brcmfmac"*
	fi
	if ls "${B}/cyfmac"* >/dev/null 2>&1 ; then
		install -D -t "${D}${nonarch_base_libdir}/firmware/cypress" -m 644 "${B}/cyfmac"*
	fi
}
