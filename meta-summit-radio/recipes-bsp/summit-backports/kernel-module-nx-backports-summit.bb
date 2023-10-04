SUMMARY = "Summit Backports for 60"

BACKPORTS_CONFIG = "${@bb.utils.contains('DISTRO_FEATURES','bluetooth','sona_nx611','sona_nx611_nbt',d)}"

RCONFLICTS:${PN} = " \
        kernel-module-60-backports-summit \
        kernel-module-lwb-backports-summit \
        kernel-module-msd45-backports-summit \
        kernel-module-msd50-backports-summit \
        kernel-module-qcacld-backports-summit \
        "

require summit-backports.inc radio-stack-nx-version.inc
