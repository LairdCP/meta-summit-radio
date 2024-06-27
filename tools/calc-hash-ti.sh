#! /bin/sh

[ -z "${1}" ] && { echo "Usage: $0 <version>"; exit 1; }

ver=${1}

[ -z "${RFPROS_FILESHARE_USER}" ] || \
  RFPROS_FILESHARE_AUTH="${RFPROS_FILESHARE_USER}:${RFPROS_FILESHARE_PASS}@"

file="radio-stack-ti-hashes.inc"
prefix="https://${RFPROS_FILESHARE_AUTH}files.devops.rfpros.com/builds/linux"

calc_file () {
  wget -t 1 -T 4 "${prefix}/${1}/${ver}/${2}" || exit 1
  echo "SRC_URI[${3}.md5sum] = \"$(md5sum "${2}" | awk '{print $1}')\"" >> ${file}
  echo "SRC_URI[${3}.sha256sum] = \"$(sha256sum "${2}" | awk '{print $1}')\"" >> ${file}
  rm -f "${2}"
}

printf 'RADIO_VERSION = "%s"\n' "${ver}" > ${file}

calc_file "summit_supplicant/laird" "summit_supplicant-src-${ver}.tar.gz" "summit-supplicant-src"
calc_file "lrd-network-manager/src" "summit-network-manager-src-${ver}.tar.xz" "summit-network-manager"
calc_file "backports/laird" "summit-backports-${ver}.tar.bz2" "summit-backports"

for i in WW US JP EU CA AU
do
  calc_file "firmware" "summit-ti351-${i}-firmware-${ver}.tar.bz2" "ti351-${i}-firmware"
done
