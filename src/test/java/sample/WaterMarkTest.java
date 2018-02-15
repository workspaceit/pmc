package sample;

import com.workspaceit.pmc.config.WebConfig;
import com.workspaceit.pmc.entity.Watermark;
import com.workspaceit.pmc.service.WatermarkService;
import com.workspaceit.pmc.util.WatermarkUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by mi_rafi on 1/4/18.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class WaterMarkTest {



    private WatermarkUtil watermarkUtil;
    private WatermarkService watermarkService;

    @Autowired
    public void setWatermarkUtil(WatermarkUtil watermarkUtil) {
        this.watermarkUtil = watermarkUtil;
    }

    @Autowired
    public void setWatermarkService(WatermarkService watermarkService) {
        this.watermarkService = watermarkService;
    }


    @Test
    public void waterMarkLogoTest(){
       Watermark watermark =  this.watermarkService.getById(1);

      /*  try {
            BufferedImage sourceImage =   watermarkUtil.addWatermark("/home/mi/Pictures/water mark/sample1.jpg",watermark);
            ImageIO.write(sourceImage, "png", new File("/home/mi/Pictures/water mark/output2w_.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try {
            ImageIO.write(watermarkUtil.addWatermarkLogo("/home/mi/Pictures/water mark/sample1.jpg",watermark),
                    "png", new File("/home/mi/Pictures/water mark/output2_.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}