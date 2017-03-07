package scouter.agent.trace;

import scouter.lang.step.ThreadCallPossibleStep;
import scouter.util.IntKeyLinkedMap;

public class TransferMap {
	public static class ID {
		public long gxid;
		public long caller;
		public long callee;
		public byte xType;
		public long callerThreadId;
		public ThreadCallPossibleStep tcStep;

		public ID(long gxid, long caller, long callee, byte xType) {
			this(gxid, caller, callee, xType, 0L, null);
		}

		public ID(long gxid, long caller, long callee, byte xType, long callerThreadId, ThreadCallPossibleStep tcStep) {
			this.gxid = gxid;
			this.caller = caller;
			this.callee = callee;
			this.xType = xType;
			this.callerThreadId = callerThreadId;
			this.tcStep = tcStep;
		}
	}

	private static IntKeyLinkedMap<ID> map = new IntKeyLinkedMap<ID>().setMax(10001);

	public static void put(int hash, long gxid, long caller, long callee, byte xType) {
		put(hash, gxid, caller, callee, xType, 0L, null);
	}

	public static void put(int hash, long gxid, long caller, long callee, byte xType, long callerThreadId, ThreadCallPossibleStep tcStep) {
		map.put(hash, new ID(gxid, caller, callee, xType, callerThreadId, tcStep));
	}

	public static void remove(int hash) {
		map.remove(hash);
	}

	public static ID get(int hash) {
		return map.get(hash);
	}


}
