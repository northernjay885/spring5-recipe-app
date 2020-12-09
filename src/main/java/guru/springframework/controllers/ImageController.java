package guru.springframework.controllers;


import guru.springframework.services.DisplayRecipeService;
import guru.springframework.services.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
public class ImageController {

    private final ImageService imageService;
    private final DisplayRecipeService displayRecipeService;

    public ImageController(ImageService imageService, DisplayRecipeService displayRecipeService) {
        this.imageService = imageService;
        this.displayRecipeService = displayRecipeService;
    }

    @GetMapping("recipe/{recipeId}/image")
    public String displayUploadImagePage(@PathVariable String recipeId, Model model) {
        model.addAttribute("recipe", displayRecipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/imageuploadform";
    }

    @PostMapping("recipe/{recipeId}/image")
    public String uploadImage(@PathVariable String recipeId, @RequestParam("imagefile")MultipartFile file) {

        imageService.saveImageFile(Long.valueOf(recipeId), file);

        return "redirect:/recipe/" + recipeId + "/show";
    }
}
