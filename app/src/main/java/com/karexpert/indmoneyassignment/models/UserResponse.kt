package com.karexpert.indmoneyassignment.models



class UserResponse(val id : Int,
                   val open_issues_count : String,
                   val license: License,
                   val permissions: Permissions,
                   val description: String) {
}