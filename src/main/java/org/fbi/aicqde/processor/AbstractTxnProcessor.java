package org.fbi.aicqde.processor;

import org.fbi.aicqde.domain.starring.T9999Response.TOA9999;
import org.fbi.aicqde.internal.AppActivator;
import org.fbi.linking.codec.dataformat.SeperatedTextDataFormat;
import org.fbi.linking.processor.standprotocol10.Stdp10Processor;
import org.osgi.framework.BundleContext;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * User: zhanrui
 * Date: 13-12-18
 * Time: ����6:16
 */
public class AbstractTxnProcessor extends Stdp10Processor {

    protected String getErrorRespMsgForStarring(String errCode) throws Exception {
        TOA9999 toa = new TOA9999();
        //toa.setErrCode(errCode);
        toa.setErrMsg("����ʧ��-" + getRtnMsg(errCode));
        String starringRespMsg;
        Map<String, Object> modelObjectsMap = new HashMap<String, Object>();
        modelObjectsMap.put(toa.getClass().getName(), toa);
        SeperatedTextDataFormat starringDataFormat = new SeperatedTextDataFormat(toa.getClass().getPackage().getName());
        starringRespMsg = (String) starringDataFormat.toMessage(modelObjectsMap);
        return starringRespMsg;
    }

    //���ݷ������ȡ������Ϣ
    private String getRtnMsg(String rtnCode) {
        BundleContext bundleContext = AppActivator.getBundleContext();
        URL url = bundleContext.getBundle().getEntry("rtncode.properties");

        Properties props = new Properties();
        try {
            props.load(url.openConnection().getInputStream());
        } catch (Exception e) {
            throw new RuntimeException("�����������ļ���������", e);
        }
        String property = props.getProperty(rtnCode);
        if (property == null) {
            property = "δ�����Ӧ�Ĵ�����Ϣ(������:" + rtnCode + ")";
        }
        return property;
    }
}
