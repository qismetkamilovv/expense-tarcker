package az.keytd.expensetracker.controller;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.keytd.expensetracker.entities.Asset;
import az.keytd.expensetracker.service.AssetService;

@RestController
@RequestMapping("asset")
public class AssetController {

    @Autowired
    private AssetService assetService;


    // TODO create new requestbody AssetCreateRequest
    @PostMapping("create")
    public Asset createAsset(@RequestParam String name, @RequestParam String assetType,
            @RequestParam Double value, @RequestParam String description) {
        return assetService.createAsset(name, assetType, value, description);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id){
        assetService.deleteAsset(id);
        return ResponseEntity.ok().build();
    }
}
