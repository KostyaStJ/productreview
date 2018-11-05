package com.astound.presentation.productreviews.repository;

import com.astound.presentation.productreviews.entities.Category;

import java.util.ArrayList;
import java.util.List;


public class CategoryRepository
{
	private static List<Category> categories = new ArrayList<>();

	public static List<Category> getCategories()
	{
		return categories;
	}
}
