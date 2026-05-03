package ro.unibuc.helpdesk.service;

import ro.unibuc.helpdesk.model.Category;
import ro.unibuc.helpdesk.repository.CategoryRepository;

import java.util.List;

public class CategoryService {

    private final CategoryRepository repository;
    private final AuditService audit;

    public CategoryService(CategoryRepository repository, AuditService audit) {
        this.repository = repository;
        this.audit = audit;
    }

    public void addCategory(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name is required.");
        }

        repository.create(new Category(name));
        audit.log("ADD_CATEGORY");
    }

    public List<Category> getAllCategories() {
        audit.log("LIST_CATEGORIES");
        return repository.findAll();
    }
}