package org.fbi.aicqde.processor;

import org.fbi.linking.processor.Processor;
import org.fbi.linking.processor.ProcessorConfig;
import org.fbi.linking.processor.ProcessorRequest;
import org.fbi.linking.processor.ProcessorResponse;
import org.fbi.linking.processor.standprotocol10.Stdp10ProcessorRequest;
import org.fbi.linking.processor.standprotocol10.Stdp10ProcessorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class TxnMainProcessor implements Processor, ProcessorConfig {
    public Logger logger = LoggerFactory.getLogger(this.getClass());

	public abstract void execute(Stdp10ProcessorRequest request, Stdp10ProcessorResponse response);

    @Override
	public void service(ProcessorRequest request, ProcessorResponse response) {
//		Map<String, Object> model = new HashMap<String, Object>();
		execute((Stdp10ProcessorRequest)request, (Stdp10ProcessorResponse)response);
	}

}
