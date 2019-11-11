## Introduction
All events that occur on exchanges (placed and canceled orders, trades, and so on) are received by dxFeed in individual formats and in 
accordance with the protocols of each exchange. Hereupon dxFeed normalizes all the data into its own records with a predefined set of 
fields – dxFeed internal QD-model (Quote Distribution).
**NB!** For clients’ access to data feeds we provide [dxFeed API](https://www.dxfeed.com/dxfeed-apis/) with a simplified set of events model. You can [learn more](https://docs.dxfeed.com/dxfeed/api/index.html) about events and 
classes in dxFeed API.
Records in QD-model are described below.

## Exchange codes for US Equities and Options
On US market, different exchanges may trade the same instruments. Cumulative feed of events from all exchanges altogether we call **composite**. 
Individual events from each exchange we call **regional**. To distinguish regional events, each exchange has a corresponding code. The list 
of the codes you can find in the tables below.
In Europe, there are no regional events: each financial instrument has different ticker symbol at each European exchange.

CTA/UTP data feed

| Code | Related exchange | MIC |
| ---- | ---- | ---- |
| **A** | NYSE MKT Stock Market | AMEX |
| **B** | NASDAQ OMX BX Stock Exchange | XBOS |
| **C** | National Stock Exchange | XCIS |
| **D** | Financial Industry Regulatory Authority | FINR |
| **E** | Consolidated Quotation System  |  |
| **I** | International Securities Exchange | XISE |
| **J** | Cboe EDGA U.S. Equities Exchange | EDGA |
| **K** | Cboe EDGX U.S. Equities Exchange | EDGX |
| **M** | Chicago Stock Exchange | XCHI |
| **N** | New York Stock Exchange | XNYS |
| **P** | NYSE Arca SM | ARCX |
| **Q** | NASDAQ OMX | XNAS |
| **V** | Investors’ Exchange LLC | IEXG |
| **W** | Cboe Stock Exchange | CBSX |
| **X** | NASDAQ OMX PSX Stock Exchange | XPSX |
| **Y** | Cboe BYX U.S. Equities Exchange | BATY (BYX) |
| **Z** | Cboe BZX U.S. Equities Exchange | BATS (BZX) |

Nasdaq Basic data feed

| Code | Related exchange | MIC |
| ---- | ---- | ---- |
| **B** | NASDAQ OMX BX Stock Exchange | XBOS |
| **L** | NASDAQ/FINRA TRF Carteret | FINN |
| **Q** | NASDAQ OMX | XNAS |
| **X** | NASDAQ OMX PSX Stock Exchange | XPSX |

OTC data feed

| Code | Related exchange | MIC |
| ---- | ---- | ---- |
| **U** | OTCBB | XOTC |
| **V** | OTC Markets | OTCM |

US Options

| Code | Related exchange | MIC |
| ---- | ---- | ---- |
| **A** | NYSE MKT | XASE |
| **B** | Boston Options Exchange | XBOX |
| **C** | Cboe Global Markets | XCBO |
| **D** | MIAX EMERALD | EMLD |
| **E** | Cboe EDGX Options | EDGO |
| **H** | ISE GEMINI | GMNI |
| **I** | International Securities Exchange | XISX |
| **J** | ISE MERCURY Exchange | MCRY |
| **M** | Miami International Securities Exchange | XMIO |
| **N** | NYSE ARCA Options | ARCO |
| **P** | MIAX Pearl | MPRL |
| **Q** | NASDAQ Options Market | XNDQ |
| **T** | NASDAQ OMX BX Options | XBXO |
| **W** | C2 Options Exchange | C2OX |
| **X** | NASDAQ OMX PHLX | XPHL |
| **Z** | Cboe BZX Options Exchange | BATO |

## Records description
Records in QD-model are described below. If you plan to use [dxFeed API](https://www.dxfeed.com/dxfeed-apis/) 
to process dxFeed market data, please check [this documentation](https://docs.dxfeed.com/dxfeed/api/index.html).

### EventFlags field
Any record may contain **EventFlags** field that is meant for the reconstruction of order books, options chains, or charts. Its values 
describe the order in which records are processed. QD text/csv format does not contain this field in the head description, but this 
flag may appear in the end of any string. If there is no Event flag, it is considered false.
**NB!** To learn order book reconstruction algorithm and corresponding details, see dxFeed [Order Book Reconstruction document](https://downloads.dxfeed.com/specifications/dxFeed-Order-Book-Reconstruction.pdf).

* **TX_PENDING** indicates a pending transactional update. When TX_PENDING is true, it means that an ongoing transaction update, that 
spans multiple events, is in process. 
* **REMOVE_EVENT** indicates that the event with the corresponding index has to be removed.
* **SNAPSHOT_BEGIN** indicates when the loading of a snapshot starts. Snapshot load starts on new subscription and the first indexed 
event that arrives for each exchange code (in case of regional record) on new subscription may have SNAPSHOT_BEGIN set to true. It means, that an ongoing snapshot consisting of multiple events is incoming. 
* **SNAPSHOT_END** or **SNAPSHOT_SNIP** indicates the end of a snapshot. The difference between SNAPSHOT_END and SNAPSHOT_SNIP is the 
following: SNAPSHOT_END indicates that the data source had sent all the data pertaining to the subscription for the corresponding 
indexed event, while SNAPSHOT_SNIP indicates that some limit on the amount of data was reached and while there still might be more 
data available, it will not be provided.
* **SNAPSHOT_MODE** instructs dxFeed to use snapshot mode. It is intended to be used only for publishing to activate (if not yet activated) 
snapshot mode. The difference from SNAPSHOT_BEGIN flag is that SNAPSHOT_MODE only switches on snapshot mode without starting snapshot 
synchronization protocol. 

## Quote
Snapshot of the best bid and ask prices, and other fields that may change with each quote. It represents the most recent 
information that is available about the best quote on the market at any given moment of time.
``` bash
#=Quote,EventSymbol,EventTime,BidTime,BidExchangeCode,BidPrice,BidSize,AskTime,AskExchangeCode,AskPrice,AskSize
Quote,FBGX,20180926-100000.000-0400,20180926-095959-0400,Q,297.01,25,20180926-095959-0400,Q,298.23,25
```

| Field | Description | 
| ---- | ---- |
| EventSymbol | Symbol of this event |
| EventTime | Time when this event has been registered in dxFeed system (filled only for historical data; not filled for real-time data feed) |
| BidTime | Time of the last bid change |
| BidExchangeCode | Bid exchange code |
| BidPrice | Bid price |
| BidSize | Bid size |
| AskTime | Time of the last ask change |
| AskExchangeCode | Ask exchange code |
| AskPrice | Ask price |
| AskSize | Ask size | 

## Quote&X (regional quote)
Similar to [Quote](#quote), but represents best bid and offer for a given exchange code. For possible values of X 
please refer to [Exchange codes](#exchange-codes-for-us-equities-and-options) chapter.

```
#=Quote&Z,EventSymbol,EventTime,BidTime,BidPrice,BidSize,AskTime,AskPrice,AskSize
Quote&Z,MU,20180926-100000.000-0400,20180926-095959-0400,44.33,4,20180926-095959-0400,44.34,1
```

## Profile
Snapshot of a security instrument description. It represents the most recent information that is available about the traded security on the market at 
any given moment of time.

```
#=Profile,EventSymbol,EventTime,Beta,Eps,DivFreq,ExdDivAmount,ExdDivDate,HighPrice52,LowPrice52,Shares,FreeFloat,HighLimitPrice,LowLimitPrice,HaltStartTime,HaltEndTime,Flags,Description,StatusReason
Profile,FPI,20180926-100000.135-0400,0.01985423,-0.06,4,0.05,20180928,9.68,5.15,3.2867817000000004E7,NaN,NaN,NaN,0,0,10,"Farmland Partners Inc","Trading Range Indication"
```

| Field | Description |
| ---- | ---- |
| EventSymbol | Symbol of this event
| EventTime | Time when this event has been registered in dxFeed system (filled only for historical data; not filled for real-time data feed) |
| Beta | The correlation coefficient of the instrument to the S&P500 index (calculated, or received from other data providers) |
| Eps | Earnings per share (the company's profits divided by the number of shares). The value comes directly from the annual quarterly accounting reports of companies. Available generally for stocks |
| DivFreq | Frequency of cash dividends payments per year (calculated) |
| ExdDivAmount | The amount of the last paid dividend |
| ExdDivDate | Date of the last dividend payment |
| HighPrice52 | The maximum price for 52 weeks (calculated from the current date) |
| LowPrice52 | The minimum price for 52 weeks (calculated from the current date) |
| Shares | Shares outstanding. In general, this is the total number of shares issued by this company (only for stocks) |
| FreeFloat | The number of shares outstanding that are available to the public for trade. This field always has NaN value. |
| HighLimitPrice | Maximum (high) allowed price |
| LowLimitPrice | Minimum (low) allowed price |
| HaltStartTime | Start time of the trading halt interval |
| HaltEndTime | End time of the trading halt interval |
| Flags | This field contains several individual flags encoded as an integer number the following way:<br><br>&ensp;31...4&ensp;&ensp; 3&ensp;&ensp;&ensp;2&ensp;&ensp;1&ensp;&ensp;&ensp;0<br>+--------+----+----+----+----+<br>:&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;:&ensp;&ensp;SSR&ensp;:&ensp;Status&ensp;:<br>+--------+----+----+----+----+<br><br>1. SSR (shortSaleRestriction) – special mode of protection against “shorting the market”, this field is optional (0 – undefined, 1 – active, 2 – inactive)<br><br>2. Status (tradingStatus) - the state of the instrument (0 – status undefined, 1 – trading is halted, 2 – trading is active)|
| Description | Description of the security instrument |
| StatusReason | The reason why trading is halted |
