package com.example.cscon25sampleapp.data.profile

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileScreenLocalDataSource @Inject constructor() {

    suspend fun getRandomHighlightCoverName(): String = withContext(Dispatchers.Default) {
        val highlights = listOf(
            "Travel", "Foodie", "Memories", "Adventure", "Nature",
            "Friends", "Family", "Pets", "Fitness", "Selfies",
            "Throwback", "Beach", "Sunset", "Mountains", "City Life",
            "Fashion", "Style", "Shopping", "Workouts", "Books",
            "Music", "Concerts", "Movies", "TV Shows", "Sports",
            "Gaming", "Tech", "Motivation", "Quotes", "Art",
            "Photography", "DIY", "Home Decor", "Cooking", "Baking",
            "Coffee", "Tea", "Parties", "Birthdays", "Celebrations",
            "Love", "Couple Goals", "Weddings", "Holidays", "Christmas",
            "New Year", "Halloween", "Summer", "Winter", "Spring",
            "Autumn", "Road Trips", "Camping", "Hiking", "Cycling",
            "Running", "Yoga", "Meditation", "Wellness", "Skincare",
            "Makeup", "Hairstyles", "Nails", "Jewelry", "Luxury",
            "Cars", "Motorcycles", "Boats", "Planes", "Space",
            "Science", "History", "Architecture", "Education", "Coding",
            "Startups", "Entrepreneurship", "Finance", "Investing", "Stocks",
            "Crypto", "NFTs", "Business", "Marketing", "Networking",
            "Volunteering", "Charity", "Animals", "Wildlife", "Zoos",
            "Aquariums", "Gardening", "Farming", "Sustainable Living", "Eco-Friendly",
            "Minimalism", "Vintage", "Retro", "80s", "90s"
        )
        highlights.random()
    }
}