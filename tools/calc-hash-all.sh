#! /bin/sh

dir=$(dirname "${0}")

${dir}/calc-hash-60.sh "${1}"
${dir}/calc-hash-lwb.sh "${1}"
${dir}/calc-hash-msd.sh "${1}"
${dir}/calc-hash-mt.sh "${1}"
${dir}/calc-hash-nx.sh "${1}"
${dir}/calc-hash-ti.sh "${1}"
${dir}/calc-hash-bdsdmac.sh "${1}"
