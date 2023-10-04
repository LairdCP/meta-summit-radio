SUMMARY = "Summit Backports for 60"

BACKPORTS_CONFIG = "${@bb.utils.contains('DISTRO_FEATURES','bluetooth','summit60','summit60_nbt',d)}"

RCONFLICTS_${PN} = " \
        kernel-module-lwb-backports-summit \
        kernel-module-msd45-backports-summit \
        kernel-module-msd50-backports-summit \
        kernel-module-nx-backports-summit \
        kernel-module-qcacld-backports-summit \
        "

require summit-backports.inc radio-stack-60-version.inc
