package com.rivaldo.datamahasiswa.response

import com.google.gson.annotations.SerializedName

data class ApiResponse(

	@field:SerializedName("mahasiswa")
	val mahasiswa: List<MahasiswaItem>
)

data class MahasiswaItem(

	@field:SerializedName("website-link")
	val websiteLink: String? = null,

	@field:SerializedName("text")
	val text: String? = null
)
