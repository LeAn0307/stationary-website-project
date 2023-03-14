package com.shinhands.mu.Stationary.controller.viewcontroller;

import com.shinhands.mu.Stationary.config.FileUploadUtil;
import com.shinhands.mu.Stationary.dto.CategoryDTO;
import com.shinhands.mu.Stationary.dto.ProductDTO;
import com.shinhands.mu.Stationary.service.CategoryService;
import com.shinhands.mu.Stationary.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "admin")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("product")
    public ModelMap getAllProduct() {
        List<ProductDTO> productDTOList = productService.getAllProducts();
//        productDTOList.stream().forEach(productDTO -> productDTO.setCategoriesName(categoryService.getCategoriesNameById(productDTO.getCategoryid())));
        List<CategoryDTO> categoryList = categoryService.getAllCategories();
        ProductDTO productDTO = new ProductDTO();
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("productList", productDTOList);
        modelMap.addAttribute("categoryList", categoryList);
        modelMap.addAttribute("productDTO", productDTO);
        return modelMap;
    }

    @GetMapping("product_detail")
    public ModelMap getProductDetail(@RequestParam long id) {
        ProductDTO productDTO = productService.getProductById(id);
        List<CategoryDTO> categoryList = categoryService.getAllCategories();
        ProductDTO updateProduct = new ProductDTO();
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("product", productDTO);
        modelMap.addAttribute("categoryList", categoryList);
        modelMap.addAttribute("productDTO", updateProduct);
        return modelMap;
    }

    @GetMapping("product/delete")
    public String deleteProduct(@RequestParam long id, Model model) {
        productService.deleteProduct(id);
        return "redirect:/admin/product";
    }

    @RequestMapping(path = "/product/add", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String addProduct(@RequestParam("photo") MultipartFile photo, ProductDTO productDTO, Model model) {
        String fileName = StringUtils.cleanPath(photo.getOriginalFilename());
        productDTO.setImage(fileName);

        String upload = "src/main/resources/static/images/product";
        try {
            FileUploadUtil.saveFile(upload, fileName, photo);
            productService.addProduct(productDTO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/admin/product";
    }

    @RequestMapping(path = "/product/update", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String updateProduct(@RequestParam("photo") MultipartFile photo, ProductDTO productDTO, Model model) {
        String fileName = StringUtils.cleanPath(photo.getOriginalFilename());
        if(!fileName.equals("")) {
            productDTO.setImage(fileName);
            String upload = "src/main/resources/static/images/product";
            try {
                FileUploadUtil.saveFile(upload, fileName, photo);
                productService.updateProduct(productDTO.getId(), productDTO);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            productService.updateProduct(productDTO.getId(), productDTO);
        }
        return "redirect:/admin/product";
    }
}
