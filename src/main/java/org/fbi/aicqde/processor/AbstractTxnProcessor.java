package org.fbi.aicqde.processor;

import org.apache.commons.lang.StringUtils;
import org.fbi.aicqde.domain.starring.T9999Response.TOA9999;
import org.fbi.aicqde.internal.AppActivator;
import org.fbi.linking.codec.dataformat.SeperatedTextDataFormat;
import org.fbi.linking.processor.ProcessorException;
import org.fbi.linking.processor.standprotocol10.Stdp10Processor;
import org.fbi.linking.processor.standprotocol10.Stdp10ProcessorRequest;
import org.fbi.linking.processor.standprotocol10.Stdp10ProcessorResponse;
import org.osgi.framework.BundleContext;
import org.slf4j.MDC;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * User: zhanrui
 * Date: 13-12-18
 * Time: 下午6:16
 */
public abstract class AbstractTxnProcessor extends Stdp10Processor {

    @Override
    public void service(Stdp10ProcessorRequest request, Stdp10ProcessorResponse response) throws ProcessorException, IOException {
        String txnCode = request.getHeader("txnCode");
        String tellerId = request.getHeader("tellerId");
        if (StringUtils.isEmpty(tellerId)) {
            tellerId = "TELLERID";
        }

        try {
            MDC.put("txnCode", txnCode);
            MDC.put("tellerId", tellerId);
            doRequest(request, response);
        } finally {
            MDC.remove("txnCode");
            MDC.remove("tellerId");
        }
    }

    abstract protected void doRequest(Stdp10ProcessorRequest request, Stdp10ProcessorResponse response) throws ProcessorException, IOException;

    protected String getErrorRespMsgForStarring(String errCode) throws Exception {
        TOA9999 toa = new TOA9999();
        //toa.setErrCode(errCode);
        toa.setErrMsg("交易失败-" + getRtnMsg(errCode));
        String starringRespMsg;
        Map<String, Object> modelObjectsMap = new HashMap<String, Object>();
        modelObjectsMap.put(toa.getClass().getName(), toa);
        SeperatedTextDataFormat starringDataFormat = new SeperatedTextDataFormat(toa.getClass().getPackage().getName());
        starringRespMsg = (String) starringDataFormat.toMessage(modelObjectsMap);
        return starringRespMsg;
    }

    //根据返回码获取返回信息
    private String getRtnMsg(String rtnCode) {
        BundleContext bundleContext = AppActivator.getBundleContext();
        URL url = bundleContext.getBundle().getEntry("rtncode.properties");

        Properties props = new Properties();
        try {
            props.load(url.openConnection().getInputStream());
        } catch (Exception e) {
            throw new RuntimeException("错误码配置文件解析错误", e);
        }
        String property = props.getProperty(rtnCode);
        if (property == null) {
            property = "未定义对应的错误信息(错误码:" + rtnCode + ")";
        }
        return property;
    }
}
