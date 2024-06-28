#! /bin/sh

dir=$(dirname "${0}")
ver=${1}

# shellcheck disable=SC2034
file="radio-stack-bdsdmac-hashes.inc"

# shellcheck source=/dev/null
. "${dir}/calc-common.sh"

calc_file "summit_supplicant/laird" "summit_supplicant-src-${ver}.tar.gz" "summit-supplicant-src"
calc_file "backports/laird" "summit-backports-${ver}.tar.bz2" "summit-backports"
calc_file "firmware" "summit-bdsdmac-firmware-${ver}.tar.bz2" "bdsdmac-firmware"
